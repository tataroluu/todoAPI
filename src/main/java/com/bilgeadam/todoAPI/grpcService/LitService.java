package com.bilgeadam.todoAPI.grpcService;

import com.bilgeadam.todoAPI.model.Todo;
import com.bilgeadam.todoAPI.repository.ToDoRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class LitService extends litImplBase {
    @Autowired
    ToDoRepository toDoRepository;
    @Override
    public void insert(Lit.InsertRequest request, StreamObserver<Lit.InsertResponse> responseObserver) {
        InsertRequest insertRequest = InsertRequest.newBuilder()
                .setId(request.getId())
                .setUserId(request.getUserId())
                .setTodo(request.getTodo())
                .setDate(request.getDate())
                .setPriority(request.getPriority())
                .setInProgress(request.getInProgress())
                .setDone(request.getDone())
                .build();
        InsertResponse.Builder response = InsertResponse.newBuilder();
        if (toDoRepository.save(new Todo(
                (long) request.getId(),
                (long) request.getUserId(),
                request.getTodo(),
                request.getDate(),
                (long) request.getPriority(),
                request.getInProgress(),
                request.getDone()))) {
            response.setResponseMessage(
                            "(ID : " + request.getId() +
                            " USER ID : "+request.getUserId()+
                            " TODO : "+request.getTodo()+
                            " DATE : "+request.getDate()+
                            " PRİORİTY : "+request.getPriority()+
                            " IN PROGRESS : "+request.getInProgress()+
                            " DONE : "+request.getDone()+
                            ") Eklendi.");
        } else {
            response.setResponseMessage("Bir hata oluştu.");
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
    @Override
    public void update(Lit.UpdateRequest request, StreamObserver<Lit.UpdateResponse> responseObserver) {
        UpdateResponse.Builder response = UpdateResponse.newBuilder();
        if (toDoRepository.update(new Todo((long) request.getId(),(long) request.getUserId(),request.getTodo()),(long) request.getId())) {
            response.setResponseMessage(request.getId()+" (ID : " + request.getId() +" USER ID : "+request.getUserId()+" TODO : "+request.getTodo()+ ") olarak güncellendi.");
        } else {
            response.setResponseMessage("Bir hata oluştu.");
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
    @Override
    public void delete(Lit.DeleteRequest request, StreamObserver<Lit.DeleteResponse> responseObserver) {
        DeleteResponse.Builder response = DeleteResponse.newBuilder();
        if (toDoRepository.deleteById((long) request.getId())) {
            response.setResponseMessage("ID : " + request.getId() + " Silindi.");
        } else {
            response.setResponseMessage("Bir hata oluştu.");
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
