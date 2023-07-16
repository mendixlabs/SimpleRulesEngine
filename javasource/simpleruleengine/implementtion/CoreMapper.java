package simpleruleengine.implementtion;

import com.mendix.core.CoreException;
import com.mendix.simplerulesengine.exception.RuleEngineException;
import com.mendix.simplerulesengine.model.Rule;
import simpleruleengine.proxies.Rules;

public class CoreMapper
{
    private CoreMapper(){}
    public static void mapToCore(Rules proxyRule, Rule coreRule) throws RuleEngineException, CoreException {
        if(proxyRule == null)
            throw new RuleEngineException("Proxy object cannot be null to convert into core object");

        if(coreRule == null)
            throw new RuleEngineException("Core object cannot be null to convert into proxy object");

        coreRule.setNamespace(proxyRule.getRules_RulesNamespace().getNamespace());
        coreRule.setId(proxyRule.getRuleID());
        coreRule.setCondition(proxyRule.getCondition());
        coreRule.setAction(proxyRule.getAction());
        coreRule.setSequence(proxyRule.getSequence());
        coreRule.setDescription(proxyRule.getDescription());
        coreRule.setName(proxyRule.getName());
    }
}
