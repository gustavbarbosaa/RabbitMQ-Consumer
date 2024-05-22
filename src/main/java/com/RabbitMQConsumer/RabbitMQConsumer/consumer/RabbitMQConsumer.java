package com.RabbitMQConsumer.RabbitMQConsumer.consumer;

import com.RabbitMQConsumer.RabbitMQConsumer.models.UserModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    private final JavaMailSender javaMailSender;

    @Autowired
    public RabbitMQConsumer(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RabbitListener(queues = {"${spring.rabbitmq.queue.1}"})
    public void listenerRegisterQueue(@Payload String message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("gustavoaraujohab@gmail.com");
            mailMessage.setTo(message);
            mailMessage.setSubject("Olá, " + message + ". Seja bem vindo(a)!");
            mailMessage.setText("É um prazer te-lo(a) aqui em nossa plataforma, espero que aproveite bastante!");
            javaMailSender.send(mailMessage);
        } catch (MailException me) {
            System.out.println(me.getMessage());
        }
    }

    @RabbitListener(queues = {"${spring.rabbitmq.queue.2}"})
    public void listenerEditQueue(@Payload UserModel user) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("gustavoaraujohab@gmail.com");
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Edição realizada com sucesso!");
            mailMessage.setText("Você acabou de realizar a edição de um usuário, agora o usuário está com os dados desta forma: " + user);
            javaMailSender.send(mailMessage);
        } catch (MailException me) {
            System.out.println(me.getMessage());
        }
    }
}
