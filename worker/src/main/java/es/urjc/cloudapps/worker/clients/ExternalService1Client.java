package es.urjc.cloudapps.worker.clients;

import es.urjc.cloudapps.worker.grpc.ToUpperCaseWordRequest;
import es.urjc.cloudapps.worker.grpc.ToUpperCaseWordResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static es.urjc.cloudapps.worker.grpc.WordServiceGrpc.WordServiceBlockingStub;

@Service
public class ExternalService1Client {

    @GrpcClient("externalServiceOneServer")
    private WordServiceBlockingStub client;

    @Async
    public CompletableFuture<String> toUpperCase(String word) {

        ToUpperCaseWordRequest request = ToUpperCaseWordRequest.newBuilder()
                .setWord(word)
                .build();

        ToUpperCaseWordResponse response = this.client.toUpperCaseWord(request);

        return CompletableFuture.completedFuture(response.getWord());
    }

}
