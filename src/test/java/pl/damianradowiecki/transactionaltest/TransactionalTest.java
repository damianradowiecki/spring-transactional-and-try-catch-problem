package pl.damianradowiecki.transactionaltest;

import org.junit.Test;
import org.junit.internal.Throwables;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionalTest {

    @Autowired
    private Service service;

    @Autowired
    private Component component;

    @Test
    public void everythingOK() {
        try {
            service.justCallRepository();
        } catch (RuntimeException exception) {
            String stackTrace = Throwables.getStacktrace(exception);
            assertThat(stackTrace).contains(Repository.EXCEPTION_MESSAGE);
        } catch (Exception e) {
            fail("should throw RuntimeException");
        }
    }

    @Test
    public void catchingExceptionCausesLoosingRootException() {
        try {
            service.catchRuntimeException();
        } catch (UnexpectedRollbackException exception) {
            String stackTrace = Throwables.getStacktrace(exception);
            assertThat(stackTrace).doesNotContain(Repository.EXCEPTION_MESSAGE);
        } catch (Exception e) {
            fail("should throw UnexpectedRollbackException");
        }
    }

    @Test
    public void requiringNewTransactionOnRepositoryLevelHelps() {
        service.catchRuntimeExceptionAndForceNewTransaction();
    }

    @Test
    public void noRollbackRuleOnRepositoryAlsoHelps() {
        service.catchRuntimeExceptionWhenRepositoryExceptionDoesNotCauseRollback();
    }

    @Test
    public void whenNoRollbackRuleIsInWrongPlaceItDoesNotWork(){
        try {
            component.callService();;
        } catch (UnexpectedRollbackException exception) {
            String stackTrace = Throwables.getStacktrace(exception);
            assertThat(stackTrace).doesNotContain(Repository.EXCEPTION_MESSAGE);
        } catch (Exception e) {
            fail("should throw UnexpectedRollbackException");
        }
    }
}
