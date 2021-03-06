package org.jepria.tools.apispecmatcher.core;

public class MethodMapperImpl implements MethodMapper {

  @Override
  public boolean map(SpecMethod specMethod, JaxrsMethod jaxrsMethod) {
    if (!specMethod.httpMethod().equalsIgnoreCase(jaxrsMethod.httpMethod())) {
      return false;
    }

    if (!mapPaths(specMethod.path(), jaxrsMethod.path())) {
      return false;
    }

    return true;
  }

  protected boolean mapPaths(String path1, String path2) {
    if (path1 == null && path2 == null) {
      return true;
    } else if (path1 == null || path2 == null) {
      return false;
    }

    // do not match path params
    String path1paramsIgnored = path1.replaceAll("\\{.+?\\}", "{}");
    String path2paramsIgnored = path2.replaceAll("\\{.+?\\}", "{}");

    return path1paramsIgnored.equals(path2paramsIgnored);
  }
}
