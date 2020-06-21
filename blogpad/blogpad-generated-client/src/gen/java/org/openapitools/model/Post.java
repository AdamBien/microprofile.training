package org.openapitools.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Post  {
  
  @ApiModelProperty(value = "")
  private String fileName;

  @ApiModelProperty(example = "openapi intro", value = "")
  private String title;

  @ApiModelProperty(example = "how to use ...", value = "")
  private String content;

  @ApiModelProperty(value = "")
  private Date createdAt;

  @ApiModelProperty(value = "")
  private Date modifiedAt;
 /**
   * Get fileName
   * @return fileName
  **/
  @JsonProperty("fileName")
  public String getFileName() {
    return fileName;
  }


 /**
   * Get title
   * @return title
  **/
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Post title(String title) {
    this.title = title;
    return this;
  }

 /**
   * Get content
   * @return content
  **/
  @JsonProperty("content")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Post content(String content) {
    this.content = content;
    return this;
  }

 /**
   * Get createdAt
   * @return createdAt
  **/
  @JsonProperty("createdAt")
  public Date getCreatedAt() {
    return createdAt;
  }


 /**
   * Get modifiedAt
   * @return modifiedAt
  **/
  @JsonProperty("modifiedAt")
  public Date getModifiedAt() {
    return modifiedAt;
  }



  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Post {\n");
    
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    modifiedAt: ").append(toIndentedString(modifiedAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

