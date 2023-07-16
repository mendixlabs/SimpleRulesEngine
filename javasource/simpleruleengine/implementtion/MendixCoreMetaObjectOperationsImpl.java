package simpleruleengine.implementtion;

import com.mendix.core.Core;
import com.mendix.simplerulesengine.mendixapi.IMendixCoreMetaObjectOperations;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class MendixCoreMetaObjectOperationsImpl implements IMendixCoreMetaObjectOperations
{
    IContext context = null;

    public MendixCoreMetaObjectOperationsImpl(IContext cntx)
    {
        if(cntx == null)
        {
            cntx = Core.createSystemContext();
        }
        this.context = cntx;
    }
    @Override
    public Object getValue(Object input, String memberName)
    {
        if(input instanceof IMendixObject)
        {
            return ((IMendixObject)input).getValue(this.context, memberName);
        }
        return null;
    }
    @Override
    public void setValue(Object input, String memberName, Object newValue)
    {
        if(input instanceof IMendixObject)
        {
            ((IMendixObject)input).setValue(this.context, memberName, newValue);
        }
    }
}
