package az.company.orders.client.decoder;

import az.company.orders.exception.PaymentServiceException;
import az.company.orders.exception.ProductServiceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
public class CustomErrorDecoder implements ErrorDecoder {

    ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        if (methodKey.contains("ProductClient")) {

            switch (response.status()) {
                case 404:
                    return new ProductServiceException("Product not found");
                case 409:
                    return new ProductServiceException("Insufficient stock");
            }

        }

        if (methodKey.contains("PaymentClient")) {

            switch (response.status()) {
                case 400:
                    return new PaymentServiceException("Payment failed");
                case 503:
                    return new PaymentServiceException("Payment service unavailable");
            }

        }

        return new Default().decode(methodKey, response);
    }
}
