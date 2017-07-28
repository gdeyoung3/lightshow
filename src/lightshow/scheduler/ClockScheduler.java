/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightshow.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author rferretti
 */
public class ClockScheduler implements SubscribableScheduler {
    private final int frequency;
    private final ScheduledExecutorService scheduler;
    
    private final List<SubscribableSchedulerSubscriber> pendingSubscribers;
    private Iterable<SubscribableSchedulerSubscriber> subscribers;
    private boolean hasSubscriberChanges;
    
    public ClockScheduler(int frequency) {
        this.frequency = frequency;
        scheduler = Executors.newSingleThreadScheduledExecutor();
        pendingSubscribers = new ArrayList<>();
        subscribers = new ArrayList<>();
        hasSubscriberChanges = false;
    }
    
    @Override
    public void start() {
        scheduler.scheduleAtFixedRate(() ->
        {
            if (hasSubscriberChanges) {
                subscribers = new ArrayList<>(pendingSubscribers);
                hasSubscriberChanges = false;
            }
            
            subscribers.forEach(subscriber -> {
                subscriber.scheduledEventHandler();
            });
        }, 0, frequency, TimeUnit.MILLISECONDS);
    }
    
    @Override
    public void stop() {
        scheduler.shutdownNow();
    }
    
    @Override
    public void subscribe(SubscribableSchedulerSubscriber toSubscribe) {
        pendingSubscribers.add(toSubscribe);
        hasSubscriberChanges = true;
    }
    
    @Override
    public void unsubscribe(SubscribableSchedulerSubscriber toUnsubscribe) {
        pendingSubscribers.remove(toUnsubscribe);
        hasSubscriberChanges = true;
    }
}