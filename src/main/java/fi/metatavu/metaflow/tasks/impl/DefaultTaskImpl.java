package fi.metatavu.metaflow.tasks.impl;

import fi.metatavu.metaflow.tasks.Task;

public class DefaultTaskImpl implements Task {
  
  private static final long serialVersionUID = 6113121163949736387L;

  private String uniqueId;
  
  private boolean priority;
  
  public DefaultTaskImpl() {
    // Default constructor
  }
  
  public DefaultTaskImpl(String uniqueId, boolean priority) {
    this.uniqueId = uniqueId;
    this.priority = priority;
  }

  @Override
  public String getUniqueId() {
    return uniqueId;
  }

  @Override
  public boolean getPriority() {
    return priority;
  }

}
