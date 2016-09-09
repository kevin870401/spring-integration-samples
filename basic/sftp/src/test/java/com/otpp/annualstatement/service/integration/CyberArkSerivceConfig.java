package com.otpp.annualstatement.service.integration;

import integration.com.otpp.annualstatement.service.integration.SftpOutboundTransferManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.remote.handler.FileTransferringMessageHandler;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.messaging.MessageChannel;

@Configuration
@ImportResource("classpath:/integration/SftpOutboundTransfer.xml")
public class CyberArkSerivceConfig {

    @Autowired
    CachingSessionFactory sftpSessionFactory;

    @Autowired
    MessageChannel inputChannel;

    @Bean
    public SftpOutboundTransferManager sftpOutboundTransferManager(){

        return new SftpOutboundTransferManager(sftpSessionFactory,inputChannel);
    }

//    @Bean
//    @ServiceActivator(inputChannel = "inputChannel")
//    public FileTransferringMessageHandler fileTransferringMessageHandler () {
//        FileTransferringMessageHandler fileTransferringMessageHandler = new FileTransferringMessageHandler(sftpRemoteFileTemplate(), FileExistsMode.REPLACE);
//        fileTransferringMessageHandler.setAutoCreateDirectory(true);
//        fileTransferringMessageHandler.setRemoteDirectoryExpression();
//        fileTransferringMessageHandler.
//        return sftpMessageHandler;
//    }
//    @Bean
//    private SftpRemoteFileTemplate sftpRemoteFileTemplate() {
//        SftpRemoteFileTemplate sftpRemoteFileTemplate= new SftpRemoteFileTemplate(sftpSessionFactory);
//        IntegrationFlow
//    }
}
