package fi.metatavu.metaflow.tasks;

import java.io.Serializable;

/**
 * Base interface for all tasks
 * 
 * @author Antti Leppä
 * @author Heikki Kurhinen
 */
public interface Task extends Serializable {
  
  /**
   * Returns an unique id for the task within the queue
   * 
   * @return an unique id for the task within the queue
   */
  public String getUniqueId();
  
  /**
   * Returns whether task is a priority task or not
   * 
   * @return whether task is a priority task or not
   */
  public boolean getPriority();
  
}
