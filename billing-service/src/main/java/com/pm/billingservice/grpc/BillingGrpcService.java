package com.pm.billingservice.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService  extends BillingServiceGrpc.BillingServiceImplBase {


    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest request, io.grpc.stub.StreamObserver<billing.BillingResponse> responseObserver){

         log.info("This is log for billing Request",request.toString());

         // Busines Logic hit to database and perform busines logic

        BillingResponse billingResponse = BillingResponse.
                newBuilder().setAccountId("12345").setStatus("Active")
                .build();
        responseObserver.onNext(billingResponse);
        responseObserver.onCompleted();
     }
}
