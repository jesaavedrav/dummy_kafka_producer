package com.js.kafkaclient
import javax.jms.Session

import org.apache.activemq.ActiveMQConnectionFactory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaClientApplication

fun main(args: Array<String>) {
    runApplication<KafkaClientApplication>(*args)

/*

    val connFactory = ActiveMQConnectionFactory()

    val conn = connFactory.createConnection()!!

    val sess = conn.createSession(false, Session.AUTO_ACKNOWLEDGE)!!

    val dest = sess.createQueue("SampleQueue")

    val prod = sess.createProducer(dest)!!

    val msg = sess.createTextMessage("Aqui esta el mensaje de kotlin... desde aca si sirveeeeeee")!!

    prod.send(msg)

    conn.close()

 */
}
