package com.otpp.annualstatement.service.integration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/META-INF/spring/integration/SftpOutboundTransfer.xml")
public class CyberArkSerivceConfig {

}
