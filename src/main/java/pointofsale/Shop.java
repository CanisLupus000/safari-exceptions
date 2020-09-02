package pointofsale;

import com.sun.net.httpserver.Authenticator;

import java.io.IOException;
import java.net.Socket;

class ModemDidNotConnectException extends Exception {}

class ModemLibrary {
  public static void bad() {
//    throw new Throwable();
  }
  public static void dialModem(int phone) throws ModemDidNotConnectException {

  }
}

class RetryLaterException extends Exception {

  public RetryLaterException() {
  }

  public RetryLaterException(String message) {
    super(message);
  }

  public RetryLaterException(String message, Throwable cause) {
    super(message, cause);
  }

  public RetryLaterException(Throwable cause) {
    super(cause);
  }

  public RetryLaterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
public class Shop {
  private static boolean USE_MODEM = false;

//  public static void buy(int cost, int ccnum) throws ModemDidNotConnectException, IOException {
  public static void buy(int cost, int ccnum) throws RetryLaterException {
    int retriesLeft = 3;
    boolean success = false;
    while (!success && retriesLeft > 0) {
      // ask for means of payment
      try {
        if (USE_MODEM) {
          ModemLibrary.dialModem(1234567890);
        } else {
          Socket s = new Socket("creditCard.com", 8080);
        }
        // potentially throw NoMoneyException etc.
        // get paid..
      } catch (ModemDidNotConnectException | IOException me) {
        if (--retriesLeft == 0) {
          throw new RetryLaterException(me);
        }
      }// catch (Exception me) { // Common parent of both exceptions--INCLUDES many others!
//        if (--retriesLeft == 0) {
//          throw me;
//        }
//      } //catch (ModemDidNotConnectException me) {
//        if (--retriesLeft == 0) {
//          throw me;
//        }
//      } catch (IOException me) {
//        if (--retriesLeft == 0) {
//          throw me;
//        }
//      }
    } // drop out or success??
  }

  public static void main(String[] args) {
    // customer wants to buy
    try {
      buy(1000, 12341234);

    } catch (RetryLaterException re) {
      // get human help
    }

//    catch (ModemDidNotConnectException me) {
//      // ask for help
//    } catch (IOException me) {
//      // ask for help
//    }

  }
}
