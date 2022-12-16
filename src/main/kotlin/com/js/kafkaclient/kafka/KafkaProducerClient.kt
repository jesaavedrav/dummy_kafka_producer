package com.js.kafkaclient.kafka


import com.js.kafkaclient.controllers.KafkaMessageSerializer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service
import java.util.*

class KafkaProducerClient private constructor(){

    companion object{
        private var instance: Producer<String, Any>? = null
        fun getInstance(): Producer<String, Any> {
            if (instance == null) {
                println("Creating new instance")
                val props = Properties()
                props["bootstrap.servers"] = "localhost:29092"
                props["key.serializer"] = StringSerializer::class.java
                props["value.serializer"] = KafkaMessageSerializer::class.java
                instance = KafkaProducer(props)
            }
            println("Returning instance")
            return instance!!
        }
    }

}