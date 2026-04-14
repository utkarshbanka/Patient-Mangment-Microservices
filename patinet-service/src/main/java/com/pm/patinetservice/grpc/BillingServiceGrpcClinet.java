package com.pm.patinetservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClinet {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final BillingServiceGrpc.BillingServiceBlockingStub stub;

//    public BillingServiceGrpcClinet(@Value("${billing.service.address:localhost}") String ServerAddress, @Value("${billing.service.grpc.prot:9002}") int serverPort )
//
//    {
//
//    }
    public BillingServiceGrpcClinet(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:9002}") int serverPort) {

        logger.info("BillingServiceGrpcClinet start",  serverAddress, serverPort);


        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).
                usePlaintext().
                build();
        stub = BillingServiceGrpc.newBlockingStub(channel);
    }


    public BillingResponse creteBillingAccount(String patientId, String name, String email){

        BillingRequest request = BillingRequest.newBuilder().
                setName(name).
                setEmail(email).build();
        BillingResponse response = stub.createBillingAccount(request);
        logger.info("BillingResponse",response);
        return response;

    }


}
