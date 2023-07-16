package simpleruleengine.implementtion;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.simplerulesengine.mendixapi.IMendixCoreLogger;
import simpleruleengine.proxies.constants.Constants;

public class MendixCoreLoggerImpl implements IMendixCoreLogger
{
    public static final ILogNode logNode = Core.getLogger(Constants.getLogNode());
    public void info(String message)
    {
        logNode.info(message);
    }

    public void trace(String message) {
        logNode.trace(message);
    }

    public void debug(String message) {
        logNode.debug(message);
    }

    public void error(String message, Throwable throwable) {
        logNode.error(message, throwable);
    }
}
