package io.su0.test.soccer.service;

import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.persistence.GroupRepository;
import io.su0.test.soccer.util.functional.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldUpdateData() {
        Group oldData = new Group();
        oldData.setName("Old name");
        when(groupRepository.findById(any())).thenReturn(Optional.of(oldData));
        when(groupRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        Group newData = new Group();
        newData.setName("New name");
        Result<Group, RuntimeException> result = groupService.updateGroup("test", old -> newData);

        Assert.assertEquals("New name", result.get().getName());
        verify(groupRepository, atLeastOnce()).save(newData);
    }
}
