/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightshow.scheduler;

/**
 *
 * @author rferretti
 */
public interface SubscribableScheduler {
    public void start();
    public void stop();
    public void subscribe(SubscribableSchedulerSubscriber toSubscribe);
    public void unsubscribe(SubscribableSchedulerSubscriber toUnsubscribe);
}
