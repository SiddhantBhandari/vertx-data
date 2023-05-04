package com.vertx_data.vertx_data.entities;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseModel extends Model {

  @Id
  protected long id;

  @Version
  protected long version;

  @WhenCreated
  protected Instant createdOn;

  @WhenModified
  protected Instant modifiedOn;

  /**
   *
   * @return id
   */
  public long getId() {
    return id;
  }

  /**
   *
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   *
   * @return version
   */
  public long getVersion() {
    return version;
  }

  /**
   *
   * @param version the version to set
   */
  public void setVersion(long version) {
    this.version = version;
  }

  /**
   *
   * @return createdOn
   */
  public Instant getCreatedOn() {
    return createdOn;
  }

  /**
   *
   * @param createdOn the createdOn to set
   */
  public void setCreatedOn(Instant createdOn) {
    this.createdOn = createdOn;
  }

  /**
   *
   * @return modifiedOn
   */
  public Instant getModifiedOn() {
    return modifiedOn;
  }

  /**
   *
   * @param modifiedOn the modified on to set
   */
  public void setModifiedOn(Instant modifiedOn) {
    this.modifiedOn = modifiedOn;
  }
}
