package pl.damianradowiecki.transactionaltest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;

    @Transactional
    public void justCallRepository() {
        repository.throwRuntimeException();
    }

    @Transactional
    public void catchRuntimeException() {
        try {
            repository.throwRuntimeException();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void catchRuntimeExceptionAndForceNewTransaction() {
        try {
            repository.throwRuntimeExceptionWithinNewTransaction();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void catchRuntimeExceptionWhenRepositoryExceptionDoesNotCauseRollback() {
        try {
            repository.throwRuntimeExceptionWithNoRollbackRuleOnRuntimeException();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional(noRollbackFor = RuntimeException.class)
    public void catchRuntimeExceptionAndAddNoRollbackRule() {
        try {
            repository.throwRuntimeException();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
