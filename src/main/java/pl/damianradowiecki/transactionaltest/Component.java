package pl.damianradowiecki.transactionaltest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Component
public class Component {

    @Autowired
    private Service service;

    @Transactional
    public void callService(){
        service.catchRuntimeExceptionAndAddNoRollbackRule();
    }
}
