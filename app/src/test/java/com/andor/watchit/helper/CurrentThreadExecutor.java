package com.andor.watchit.helper;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Executor which useful for unit testing
 */
public class CurrentThreadExecutor implements ExecutorService {

    @Override
    public void execute(Runnable command) {
        command.run();
    }

    @Override
    public void shutdown() {
    }

    @NotNull
    @Override
    public List<Runnable> shutdownNow() {
        return Collections.emptyList();
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, @NotNull TimeUnit unit) {
        return false;
    }

    @NotNull
    @Override
    public <T> Future<T> submit(@NotNull Callable<T> task) {
        FutureTask<T> f = new FutureTask<>(task);
        f.run();
        return f;
    }

    @NotNull
    @Override
    public <T> Future<T> submit(@NotNull Runnable task, T result) {
        FutureTask<T> f = new FutureTask<>(task, result);
        f.run();
        return f;
    }

    @NotNull
    @Override
    public Future<?> submit(@NotNull Runnable task) {
        FutureTask<?> f = new FutureTask<Void>(task, null);
        f.run();
        return f;
    }

    @NotNull
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) {
        return tasks.stream().map(this::submit).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, @NotNull TimeUnit unit) {
        return tasks.stream().map(this::submit).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return tasks.stream().map(this::submit).findFirst().get().get();
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, @NotNull TimeUnit unit) throws InterruptedException, ExecutionException {
        return tasks.stream().map(this::submit).findFirst().get().get();
    }

}