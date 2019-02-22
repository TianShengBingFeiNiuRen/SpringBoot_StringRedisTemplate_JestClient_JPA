# SpringBoot_StringRedisTemplate_JestClient_JPA
Spring Boot 整合 StringRedisTemplate + JestClient + JPA

**一、Spring Boot 整合 Redis (StringRedisTemplate)**

1.maven依赖
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-redis</artifactId>
            <version>2.1.5.RELEASE</version>
        </dependency>
```
跳转: [**RedisConfig.java**](https://github.com/TianShengBingFeiNiuRen/SpringBoot_StringRedisTemplate_JestClient_JPA/blob/master/stock-market-macd-calculate/src/main/java/com/blockchaindata/stockmarketmacdcalculate/config/RedisConfig.java)

**二、Spring Boot 整合 ElasticSearch5.6.9 (JestClient)**

1.maven

```
        <dependency>
            <groupId>org.elasticsearch</groupId>
            <artifactId>elasticsearch</artifactId>
            <version>5.6.9</version>
        </dependency>
        <dependency>
            <groupId>io.searchbox</groupId>
            <artifactId>jest</artifactId>
            <version>5.3.4</version>
        </dependency>
```
跳转: [**EsService.java**](https://github.com/TianShengBingFeiNiuRen/SpringBoot_StringRedisTemplate_JestClient_JPA/blob/master/stock-market-macd-calculate/src/main/java/com/blockchaindata/stockmarketmacdcalculate/service/EsService.java)

**三、Spring Boot 整合 MySQL（SpringDataJPA）**

1.maven依赖

```
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.15</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
```
跳转: [**StockInformationRepository.java**](https://github.com/TianShengBingFeiNiuRen/SpringBoot_StringRedisTemplate_JestClient_JPA/blob/master/stock-market-macd-calculate/src/main/java/com/blockchaindata/stockmarketmacdcalculate/dao/StockInformationRepository.java)

**CSDN**: [**link**](https://blog.csdn.net/weixin_39792935/article/details/87862919).
