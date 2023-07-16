package simplerulesengine.implementtion;

import com.mendix.core.Core;
import com.mendix.simplerulesengine.exception.MendixAPIExecutionException;
import com.mendix.simplerulesengine.mendixapi.IMendixMicroflowCall;
import com.mendix.systemwideinterfaces.core.IContext;

public class MendixMicroflowCallImpl implements IMendixMicroflowCall
{
    IContext context;

    public MendixMicroflowCallImpl(IContext cntx)
    {
        if(cntx == null)
        {
            cntx = Core.createSystemContext();
        }
        this.context = cntx;
    }
    @Override
    public void call(String mfName, Object mfParam) throws MendixAPIExecutionException {

        try
        {
            var mfCallBuilder = Core.microflowCall(mfName);
            if(mfParam != null)
            {
                mfCallBuilder = mfCallBuilder.withParam("data", mfParam);
            }
            mfCallBuilder.execute(this.context);
        }
        catch(Exception ex)
        {
            throw new MendixAPIExecutionException("Error in executing microflow call API", ex);
        }
    }
}
