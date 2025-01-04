package uopeople.assignment.unit8.models.weatherstack;

public class ApiResponseError {

    private boolean success;
    private ErrorDetail error;

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public ErrorDetail getError() {
        return error;
    }

    // Setters
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError(ErrorDetail error) {
        this.error = error;
    }

    public static class ErrorDetail {
        private int code;
        private String type;
        private String info;

        // Getters
        public int getCode() {
            return code;
        }

        public String getType() {
            return type;
        }

        public String getInfo() {
            return info;
        }

        // Setters
        public void setCode(int code) {
            this.code = code;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
