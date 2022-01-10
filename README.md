# JMS
# Getting Started

created two endpoints
1. /priceListPost
 It is used to read CSV data from claspath and publish in to the queue as csv string.
 Spring Boot has a in-memory ActiveMQ instance ( similar to in-memory H2 database). 
 I configure a queue from the in-memory ActiveMQ: in Config.java. 
 The queue name is “inmemory.queue” but can be named anything.
 
 2. /priceList
 
 Will read data as message from the queue and perform the operation as desired and post back data as json to UI.
 Sample response:
 [  
    {
       "id":"106",
       "instrumentName":" EUR/USD",
       "bidPrice":1.0989,
       "askPrice":1.2012,
       "timeStamp":"01-06-2020 12:01:01:001"
    },
    {
       "id":"107",
       "instrumentName":" EUR/JPY",
       "bidPrice":119.48039999999999,
       "askPrice":120.0199,
       "timeStamp":"01-06-2020 12:01:02:002"
    },
    {
       "id":"108",
       "instrumentName":" GBP/USD",
       "bidPrice":1.24875,
       "askPrice":1.257256,
       "timeStamp":"01-06-2020 12:01:02:002"
    },
    {
       "id":"109",
       "instrumentName":" GBP/USD",
       "bidPrice":1.2486501,
       "askPrice":1.2573561,
       "timeStamp":"01-06-2020 12:01:02:100"
    },
    {
       "id":"110",
       "instrumentName":" EUR/JPY",
       "bidPrice":119.49039,
       "askPrice":120.02991,
       "timeStamp":"01-06-2020 12:01:02:110"
    }
 ]
