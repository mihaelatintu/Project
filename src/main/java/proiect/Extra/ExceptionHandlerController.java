package proiect.Extra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;
import java.util.Objects;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException() {
        return new ResponseEntity<>("The requested record was not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("Invalid argument: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>("Invalid argument: " + ex.getName() + " should be of type " + Objects.requireNonNull(ex.getRequiredType()).getSimpleName(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        System.out.println("Exception message: " + ex.getMessage()); // Add this line to print the exception message

        if (ex.getMessage().contains("UK_6dotkott2kjsp8vw4d0m25fb7")) {
            return new ResponseEntity<>("Email already exists.", HttpStatus.BAD_REQUEST);
        } else if (ex.getMessage().contains("users.phone_number")) {
            return new ResponseEntity<>("Phone number already exists.", HttpStatus.BAD_REQUEST);
        } else if (ex.getMessage().contains("bookings.uc_flight_seat")) {
            return new ResponseEntity<>("Duplicate booking: The seat is already booked for the selected flight.", HttpStatus.BAD_REQUEST);
        } else if (ex.getMessage().contains("airports.UK_8x5wlokxte7yksdsllxtxbjf0")) {
            return new ResponseEntity<>("Duplicate entry: The airport code already exists.", HttpStatus.BAD_REQUEST);
        } else if (ex.getMostSpecificCause() instanceof SQLIntegrityConstraintViolationException sqlException) {
            int errorCode = sqlException.getErrorCode();
            if (errorCode == 1451) {
                return new ResponseEntity<>("Could not delete the record because it is referenced in another table.", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("Data integrity violation: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {SQLException.class})
    public ResponseEntity<Object> handleSQLException(SQLException ex) {
        // Handle the SQL exception
        return new ResponseEntity<>("SQL Exception occurred: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Invalid request body: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMessageNotWritableException.class})
    public ResponseEntity<Object> handleHttpMessageNotWritableException(HttpMessageNotWritableException ex) {
        return new ResponseEntity<>("Failed to write response body: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
