package fi.metatavu.metaflow.tasks;

/**
 * Provider for tasks
 * 
 * @author Antti Leppä
 * @author Heikki Kurhinen
 */
public interface TaskProvider {

  /**
   * Returns next tasks in queue
   * 
   * @param <T> Task type
   * @param queueName queue name
   * @return next tasks in queue or null if not found
   */
  public <T extends Task> T getNextTask(String queueName);

  /**
   * Creates new task.
   * 
   * @param <T> task type
   * @param queueName queue the task belongs to
   * @param task task data
   * @return created task
   */
  public <T extends Task> T createTask(String queueName, T task);

  /**
   * Return whether queue is empty
   * 
   * @param queueName queue name
   * @return true if queue is empty otherwise false
   */
  public boolean isQueueEmpty(String queueName);

  /**
   * Finds a task from queue by it's unique id
   * 
   * @param queueName queue name
   * @param uniqueId task's unique id
   * @return returns a task or null if not found
   */
  public <T extends Task> T findTask(String queueName, String uniqueId);
  
}
