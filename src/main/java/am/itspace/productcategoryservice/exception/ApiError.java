package com.polixis.task2.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Hovhannes Gevorgyan on 12.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

  @Schema(description = "Http response status", example = "CONFLICT")
  private HttpStatus status;

  @Schema(description = "The exception description", example = "4121")
  private Integer errorCode;

  @Schema(description = "Timestamp showing when the error occurred")
  private LocalDateTime timestamp;

  @Schema(description = "Error message", example = "Directory not found")
  private String message;
}
