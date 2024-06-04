package com.bilgeadam.todoAPI.service;

import com.bilgeadam.todoAPI.model.Todo;
import com.bilgeadam.todoAPI.repository.ToDoRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    public static String message;
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
    @Autowired
    private ToDoRepository toDoRepository;
    @GrpcClient("lit-service")
    public litBlockingStub blockingStub;

    public List<Todo> getAll() {
        return toDoRepository.getAll();
    }
    public boolean remove(Long id) {
        boolean isDeleted = false;
        try {
            blockingStub = litGrpc.newBlockingStub(channel);
            Lit.DeleteRequest deleteRequest = Lit.DeleteRequest.newBuilder().setId(Math.toIntExact(id)).build();
            Lit.DeleteResponse deleteResponse = blockingStub.delete(deleteRequest);
            message="Delete Response Message --> " + deleteResponse.getResponseMessage();
            System.out.println(message);
            isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
    public boolean update(Todo todo, Long id) {
        boolean isUpdated = false;
        try {
            blockingStub = litGrpc.newBlockingStub(channel);
            Lit.UpdateRequest updateRequest = Lit.UpdateRequest.newBuilder()
                    .setId(Math.toIntExact(todo.getID()))
                    .setUserId(Math.toIntExact(todo.getUserId()))
                    .setTodo(todo.getTodo()).build();
            Lit.UpdateResponse updateResponse = blockingStub.update(updateRequest);
            message="Update Response Message --> " + updateResponse.getResponseMessage();
            System.out.println(message);
            isUpdated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }
    public boolean save(Todo todo) {
        blockingStub = litGrpc.newBlockingStub(channel);
        boolean result = false;
        try {
            Lit.InsertRequest insertRequest = Lit.InsertRequest.newBuilder()
                    .setId(Math.toIntExact(todo.getID()))
                    .setUserId(Math.toIntExact(todo.getUserId()))
                    .setTodo(todo.getTodo())
                    .setDate(todo.getDateTime())
                    .setPriority(Math.toIntExact(todo.getPriority()))
                    .setInProgress(todo.getInProgress())
                    .setDone(todo.getDone())
                    .build();
            Lit.InsertResponse insertResponse = blockingStub.insert(insertRequest);
            message="Insert Response Message --> " + insertResponse.getResponseMessage();
            System.out.println(message);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
