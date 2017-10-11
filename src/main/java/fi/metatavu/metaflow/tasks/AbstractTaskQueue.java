package fi.metatavu.metaflow.tasks;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract base class for all task queues
 * 
 * @author Antti Leppä
 *
 * @param <T> task type
 */
public abstract class AbstractTaskQueue<T extends Task> implements TaskQueue<T> {
  
  @Inject
  private TaskProvider taskProvider;
  
  @Inject
  private Logger logger;
  
  private boolean running;
  
  @PostConstruct
  public void postConstruct() {
    running = true;
  }

  @PreDestroy
  public void preDestroy() {
    running = false;
  }

  /**
   * Returns unique name for task queue
   * 
   * @return unique name for task queue
   */
  public abstract String getName();
  
  /**
   * Returns next task or null if queue is empty
   * 
   * @return next task or null if queue is empty
   */
  public T next() {
    if (!running) {
      return null;
    }
    
    return taskProvider.getNextTask(getName());
  }

  /**
   * Enqueus new task to the queue. If priority flag is true, the task will be prepended to 
   * the front of the queue otherwise the task will be appened to the end of the queue
   * 
   * @param priority whether the task is a priority task or not
   * @param newTask taks
   */
  public void enqueueTask(T newTask) {
    if (!running) {
      return;
    }
    
    if (newTask.getPriority()) {
      logger.log(Level.INFO, () -> String.format("Added priority task to queue %s", getName())); 
    }
    
    Task existingTask = taskProvider.findTask(getName(), newTask.getUniqueId());
    if (existingTask == null) {
      taskProvider.createTask(getName(), newTask);
    } else if (!existingTask.getPriority() && newTask.getPriority()) {
      taskProvider.prioritizeTask(getName(), newTask);
    }
  }

  /**
   * Returns whether current queue is empty
   * 
   * @return true if current queue is empty, false otherwise
   */
  public boolean isEmpty() {
    return taskProvider.isQueueEmpty(getName());
  }
  
  /**
   * Stops task queue
   */
  public void stop() {
    running = false;
  }
  
}
