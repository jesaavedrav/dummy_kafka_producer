### This is a dummy kafka producer that sends a message to a kafka topic

The usage is pretty straightforward:
1. Clone the repo
2. Install gradle dependencies
3. Run the producer

The producer will automatically create the topic you want to send the message to.

Here is a curl:

```
curl --location 'http://localhost:8881/kafka-client/api/event' \
--header 'topic: your-topic-here' \
--header 'Content-Type: application/json' \
--data '{
    //your raw data here
}
}'
```