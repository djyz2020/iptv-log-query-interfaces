package com.nudt.query.service;

import com.nudt.query.entity.OfflineLog;
import com.nudt.query.entity.OnlineLog;
import com.nudt.query.entity.RangeEntity;
import com.nudt.query.entity.TopoLog;
import io.searchbox.client.JestClient;
import io.searchbox.core.Count;
import io.searchbox.core.CountResult;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by haibozhang on 2018/11/9.
 */

public class CommonQueryService{

    @Autowired
    private JestClient jestClient;

    public List<TopoLog> topoQuery(String index, String type, Map<String, Object> paramMap, int from, int size) throws IOException {
        SearchResult searchResult = getSearchResult(index, type, paramMap, from, size);
        return searchResult.getSourceAsObjectList(TopoLog.class);
    }

    public List<TopoLog> topoShouldQuery(String index, String type, Map<String, Object> paramMap, int minimumShould, int from, int size) throws IOException {
        SearchResult searchResult = shouldQuery(index, type, paramMap, minimumShould, from, size);
        return searchResult.getSourceAsObjectList(TopoLog.class);
    }

    public List<OnlineLog> onlineQuery(String index, String type, Map<String, Object> paramMap,
                                       Map<String, Object> conditionMap, int from, int size) throws IOException {
        SearchResult result = getSearchResult(index, type, paramMap, from, size);
        return result.getSourceAsObjectList(OnlineLog.class);
    }

    public List<OfflineLog> offlineQuery(String index, String type,
                                         Map<String, Object> paramMap, int from, int size) throws IOException {
        SearchResult result = getSearchResult(index, type, paramMap, from, size);
        return result.getSourceAsObjectList(OfflineLog.class);
    }

    // 普通查询
    private SearchResult getSearchResult(String index, String type, Map<String, Object> paramMap,
                                         int from, int size) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(paramMap != null && paramMap.size() > 0){
            for(Map.Entry<String, Object> entry : paramMap.entrySet()){
                /*boolQueryBuilder.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()).operator(Operator.AND));*/
                boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(entry.getKey(), entry.getValue()));
            }
        }
        searchSourceBuilder = searchSourceBuilder.query(boolQueryBuilder);
        if(from > -1 && size > -1){
            searchSourceBuilder = searchSourceBuilder.from(from).size(size);
        }
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(index)
                .addType(type)
                .build();
        return jestClient.execute(search);
    }

    // 普通查询
    private SearchResult shouldQuery(String index, String type, Map<String, Object> shouldMap, int minimumShould,
                                         int from, int size) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(shouldMap != null && shouldMap.size() > 0){
            for(Map.Entry<String, Object> entry : shouldMap.entrySet()){
//                boolQueryBuilder.should(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()).operator(Operator.AND));
                boolQueryBuilder.should(QueryBuilders.matchPhraseQuery(entry.getKey(), entry.getValue()));
            }
        }
        searchSourceBuilder = searchSourceBuilder.query(boolQueryBuilder.minimumShouldMatch(minimumShould));
        if(from > -1 && size > -1){
            searchSourceBuilder.from(from).size(size);
        }
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(index)
                .addType(type)
                .build();
        return jestClient.execute(search);
    }

    // 多索引查询
    public SearchResult getMultiIndexSearchResult(List<String> indexList, List<String> typeList,
             Map<String, Object> paramMap, RangeEntity rangeEntity, int from, int size) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(paramMap != null && paramMap.size() > 0){
            for(Map.Entry<String, Object> entry : paramMap.entrySet()){
//                boolQueryBuilder.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()).operator(Operator.AND));
                boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(entry.getKey(), entry.getValue()));
            }
        }
        searchSourceBuilder.query(boolQueryBuilder);
        RangeQueryBuilder rangeQueryBuilder = getRangeQueryBuilder(rangeEntity);
        if(rangeQueryBuilder != null){ // 范围查询
            searchSourceBuilder = searchSourceBuilder.postFilter(rangeQueryBuilder);
        }
        if(from > -1 && size > -1){
            searchSourceBuilder = searchSourceBuilder.from(from).size(size);
        }
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndices(indexList)
                .addTypes(typeList)
                .build();
        return jestClient.execute(search);
    }

    // 正则查询
    public List<TopoLog> getRegexpResult(String index, String type, Map<String, Object> must_regexp,
                                         Map<String, Object> must_not_regexp) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(must_regexp != null && must_regexp.size() > 0){
            for(Map.Entry<String, Object> entry : must_regexp.entrySet()){
                boolQueryBuilder.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()).operator(Operator.AND));
            }
        }
        if(must_not_regexp != null && must_not_regexp.size() > 0){
            for(Map.Entry<String, Object> entry : must_not_regexp.entrySet()){
                boolQueryBuilder.mustNot(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()).operator(Operator.AND));
            }
        }
        searchSourceBuilder = searchSourceBuilder.query(boolQueryBuilder);
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(index)
                .addType(type)
                .build();
        SearchResult searchResult = jestClient.execute(search);
        return searchResult.getSourceAsObjectList(TopoLog.class);
    }

    // 模糊查询
    public List<TopoLog> getFuzzySearchResult(String index, String type, Map<String, Object> paramMap,
                                              Map<String, Object> fuzzyMap) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(paramMap != null && paramMap.size() > 0){
            for(Map.Entry<String, Object> entry : paramMap.entrySet()){
                boolQueryBuilder.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()).operator(Operator.AND));
            }
        }
        if(fuzzyMap != null && fuzzyMap.size() > 0){
            for(Map.Entry<String, Object> entry : fuzzyMap.entrySet()){
                boolQueryBuilder.must(QueryBuilders.fuzzyQuery(entry.getKey(), entry.getValue()));
            }
        }
        searchSourceBuilder = searchSourceBuilder.query(boolQueryBuilder);
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(index)
                .addType(type)
                .build();
        SearchResult searchResult = jestClient.execute(search);
        return searchResult.getSourceAsObjectList(TopoLog.class);
    }

    // 统计查询(must||should)
    public double countQuery(String index, String type, Map<String, Object> paramMap, int minimumShould) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (paramMap != null && paramMap.size() > 0) {
            if(minimumShould > 0){
                for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
//                    boolQueryBuilder.should(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()).operator(Operator.AND));
                    boolQueryBuilder.should(QueryBuilders.matchPhraseQuery(entry.getKey(), entry.getValue()));
                }
            }else{
                for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
//                    boolQueryBuilder.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()).operator(Operator.AND));
                    boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(entry.getKey(), entry.getValue()));
                }
            }
        }
        if(minimumShould > 0){
            searchSourceBuilder = searchSourceBuilder.query(boolQueryBuilder.minimumShouldMatch(minimumShould));
        }else{
            searchSourceBuilder = searchSourceBuilder.query(boolQueryBuilder);
        }
        Count count = new Count.Builder()
                .query(searchSourceBuilder.toString())
                .addIndex(index)
                .addType(type)
                .build();
        CountResult countResult = jestClient.execute(count);
        System.out.println(countResult.getJsonString());
        return countResult.getCount();
    }

    // 范围查询
    public SearchResult rangeQuery(String index, String type, Map<String, Object> mustMap, Map<String, Object> mustNotMap,
                                   RangeEntity rangeEntity, Map<String, SortOrder> orderBy) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(mustMap != null && mustMap.size() > 0){ // 匹配查询
            for(Map.Entry<String, Object> entry : mustMap.entrySet()){
//                boolQueryBuilder.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()).operator(Operator.AND));
                boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(entry.getKey(), entry.getValue()));
            }
        }
        if(mustNotMap != null && mustNotMap.size() > 0){
            for(Map.Entry<String, Object> entry : mustNotMap.entrySet()){
//                boolQueryBuilder.mustNot(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()).operator(Operator.AND));
                boolQueryBuilder.mustNot(QueryBuilders.matchPhraseQuery(entry.getKey(), entry.getValue()));
            }
        }
        RangeQueryBuilder rangeQueryBuilder = getRangeQueryBuilder(rangeEntity);
        if(rangeQueryBuilder != null){ // 范围查询
            searchSourceBuilder = searchSourceBuilder.query(boolQueryBuilder).postFilter(rangeQueryBuilder);
        }else{
            searchSourceBuilder = searchSourceBuilder.query(boolQueryBuilder);
        }
        if(orderBy != null && orderBy.size() > 0){ // 排序
            for(Map.Entry<String, SortOrder> entry : orderBy.entrySet()){
                searchSourceBuilder = searchSourceBuilder.sort(entry.getKey(), entry.getValue());
            }
        }
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(index)
                .addType(type)
                .build();
        return jestClient.execute(search);
    }

    // 范围查询条件
    private RangeQueryBuilder getRangeQueryBuilder(RangeEntity rangeEntity) {
        RangeQueryBuilder rangeQueryBuilder = null;
        if(rangeEntity == null){
            return rangeQueryBuilder;
        }
        String from = rangeEntity.getFrom();
        String to = rangeEntity.getTo();
        boolean equalFrom = rangeEntity.isEqualFrom();
        boolean equalTo = rangeEntity.isEqualTo();
        if (!StringUtils.isEmpty(from) || !StringUtils.isEmpty(to)) {
            rangeQueryBuilder = QueryBuilders.rangeQuery(rangeEntity.getRangeQueryName());
            if (!StringUtils.isEmpty(from)) {
                if (equalFrom) {
                    rangeQueryBuilder.gte(from);
                } else {
                    rangeQueryBuilder.gt(from);
                }
            }
            if (!StringUtils.isEmpty(to)) {
                if (equalTo) {
                    rangeQueryBuilder.lte(to);
                } else {
                    rangeQueryBuilder.lt(to);
                }
            }
        }
        return rangeQueryBuilder;
    }

    // 在线日志范围查询
    public List<OnlineLog> rangeOnlineQuery(String index, String type, Map<String, Object> mustMap,
           Map<String, Object> mustNotMap, RangeEntity rangeEntity, Map<String, SortOrder> orderBy) throws IOException {
        SearchResult searchResult = rangeQuery(index, type, mustMap, mustNotMap, rangeEntity, orderBy);
        return searchResult.getSourceAsObjectList(OnlineLog.class);
    }

    // 离线日志范围查询
    public List<OfflineLog> rangeOfflineQuery(String index, String type, Map<String, Object> mustMap,
           Map<String, Object> mustNotMap, RangeEntity rangeEntity, Map<String, SortOrder> orderBy) throws IOException {
        SearchResult searchResult = rangeQuery(index, type, mustMap, mustNotMap, rangeEntity, orderBy);
        return searchResult.getSourceAsObjectList(OfflineLog.class);
    }

    // 在线日志范围查询
    public List<TopoLog> rangeTopoQuery(String index, String type, Map<String, Object> mustMap,
           Map<String, Object> mustNotMap, RangeEntity rangeEntity, Map<String, SortOrder> orderBy) throws IOException {
        SearchResult searchResult = rangeQuery(index, type, mustMap, mustNotMap, rangeEntity, orderBy);
        return searchResult.getSourceAsObjectList(TopoLog.class);
    }

}
