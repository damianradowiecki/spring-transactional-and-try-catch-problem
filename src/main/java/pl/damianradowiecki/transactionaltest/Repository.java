package pl.damianradowiecki.transactionaltest;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Repository
public class Repository {

    public static final String EXCEPTION_MESSAGE = "@Transactional";

    @Transactional
    public void throwRuntimeException(){
        throw new RuntimeException(EXCEPTION_MESSAGE);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void throwRuntimeExceptionWithinNewTransaction(){
        throw new RuntimeException();
    }

    @Transactional(noRollbackFor = RuntimeException.class)
    public void throwRuntimeExceptionWithNoRollbackRuleOnRuntimeException(){
        throw new RuntimeException();
    }
}
