package Project.Projectspring.chatex.Push.Service;


import Project.Projectspring.chatex.Chatting.Controller.ChatController;
import Project.Projectspring.chatex.Push.VO.ChatVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SelfDirtinessTracker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
public class RabbitService {

        private static final String exchange = "chat-exchange";
        private final RabbitTemplate rabbitTemplate;


        public void rabbitChatProducer(ChatVO chatVO) {
            rabbitTemplate.convertAndSend(exchange, "messages."+chatVO.getGroup_code(), chatVO);

        }


    }
