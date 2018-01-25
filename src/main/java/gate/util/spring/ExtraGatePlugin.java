package gate.util.spring;

import org.springframework.core.io.Resource;

/**
 * Holder class for a single {@link Resource} that points to a
 * GATE plugin.  {@link Init} scans for all beans in its defining
 * BeanFactory that are instances of this class, and loads the
 * plugins they point to after GATE has been initialized.
 */
public class ExtraGatePlugin {
  private Resource location;
  
  private String groupId;
  
  private String artifactId;
  
  private String version;
  
  public void setLocation(Resource location) {
    this.location = location;
  }
  
  public Resource getLocation() {
    return location;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getArtifactId() {
    return artifactId;
  }

  public void setArtifactId(String artifactId) {
    this.artifactId = artifactId;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
  
  
}
