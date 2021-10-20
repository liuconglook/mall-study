package com.belean.mall.tiny.test;

import com.belean.mall.tiny.Application;
import com.belean.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import com.belean.mall.tiny.nosql.mongodb.repository.MemberReadHistoryRepository;
import com.belean.mall.tiny.service.MemberReadHistoryService;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author belean
 * @date 2021/9/16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.JVM) // MethodSorters.NAME_ASCENDING
@Ignore
public class MockitoTest {

    /**
     * 验证行为是否执行
     */
    @Test
    public void testVerifyAction() {
        List<Integer> mock = Mockito.mock(List.class);
        mock.add(1);
        mock.clear();

        Mockito.verify(mock).add(1);
        Mockito.verify(mock, Mockito.times(1)).add(1);
        Mockito.verify(mock).clear();
    }

    /**
     * 顺序校验
     */
    @Test
    public void testInOrder() {
        List a = Mockito.mock(List.class);
        List b = Mockito.mock(List.class);
        a.add(1);
        b.add(2);
        InOrder inOrder = Mockito.inOrder(a, b);
        inOrder.verify(a).add(1);
        inOrder.verify(b).add(2);
    }

    /**
     * 验证模拟对象零交互
     */
    @Test
    public void testZeroInteractions() {
        List a = Mockito.mock(List.class);
        List b = Mockito.mock(List.class);
        List c = Mockito.mock(List.class);

        a.add(1);
        Mockito.verifyNoInteractions(b, c);
    }

    /**
     * 验证没有更多的交互
     * 确保验证全覆盖，防止漏掉对某些交互的验证
     */
    @Test
    public void testNoMoreInteractions() {
        List list = Mockito.mock(List.class);
        list.add(1);
        list.add(2);

        Mockito.verify(list).add(1);
        Mockito.verify(list).add(2);

        Mockito.verifyNoMoreInteractions(list);
    }

    /**
     * 模拟多次触发返回不同值
     */
    @Test
    public void testMockMany() {
        Iterator iterator = Mockito.mock(Iterator.class);
        // 模拟第一次返回hello，第二次或第n次返回world
        Mockito.when(iterator.next()).thenReturn("hello").thenReturn("world");

        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        assertEquals("hello world world", result);
    }

    /**
     * 模拟close时，抛出IOException异常
     * @throws IOException
     */
    @Test(expected = RuntimeException.class)
    public void testMockThrow() throws IOException {
        OutputStream mock = Mockito.mock(OutputStream.class);
        // 抛出异常，当mock.close时
        //Mockito.doThrow(new IOException()).when(mock).close();
        List list = Mockito.mock(List.class);
        Mockito.when(list.add(1)).thenThrow(new RuntimeException());

        list.add(1);
        //mock.close();
    }

    /**
     * 模拟深层对象返回
     */
    @Test
    public void testDeepStubs() {
        A a = Mockito.mock(A.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(a.getB().getName()).thenReturn("Beijing");

        assertEquals("Beijing", a.getB().getName());
    }

    /**
     * 同上
     */
    @Test
    public void testDeepStubs2() {
        A a = Mockito.mock(A.class);
        B b = Mockito.mock(B.class);
        Mockito.when(a.getB()).thenReturn(b);
        Mockito.when(a.getB().getName()).thenReturn("Beijing");

        assertEquals("Beijing", a.getB().getName());
    }

    class A {
        private B b;
        public B getB() {
            return b;
        }
        public void setB(B b) {
            this.b = b;
        }
    }

    class B {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getSex(Integer sex) {
            if (sex == 1) {
                return "man";
            } else {
                return "woman";
            }
        }
    }

    /**
     * 参数匹配
     */
    @Test
    public void testWithArgs() {
        B b = Mockito.mock(B.class);
        Mockito.when(b.getSex(1)).thenReturn("男");
        Mockito.when(b.getSex(2)).thenReturn("女");


        assertEquals("男", b.getSex(1));
        assertEquals("女", b.getSex(2));
        assertEquals(null, b.getSex(0));
    }

    /**
     * 匹配未指定的参数值，匹配其值类型
     */
    @Test
    public void testWithUnspecifiedArgs() {
        List<Integer> list = Mockito.mock(List.class);

        // 匹配参数类型
        // Mockito.anyInt(); // 匹配任意int
        // Mockito.any(B.class); // 匹配B对象
        Mockito.when(list.get(Mockito.anyInt())).thenReturn(1);
        // 参数匹配器
        //Mockito.when(list.contains(Mockito.argThat(new IsValid()))).thenReturn(true);
        Mockito.when(list.contains(Mockito.argThat(e -> (Integer) e == 1 || (Integer) e == 2))).thenReturn(true);

        assertSame(1, list.get(1));
        assertSame(1, list.get(2));
        assertTrue(list.contains(1));
        assertFalse(list.contains(3));
    }

    class IsValid implements ArgumentMatcher<Integer> {
        @Override
        public boolean matches(Integer e) {
            return e == 1 || e == 2;
        }
    }

    /**
     * 统一使用参数匹配器
     */
    @Test
    public void testWithUnifyArgs() {
        Comparator comparator = Mockito.mock(Comparator.class);
        comparator.compare("nihao", "hello");

        int compareValue = Mockito.verify(comparator).compare(Mockito.anyString(), Mockito.eq("hello"));

        assertEquals(0, compareValue);
    }

    /**
     * 参数捕获
     * 确保参数传递给了模拟对象
     */
    @Test
    public void testArgumentCaptor() {
        List list = Mockito.mock(List.class);
        ArgumentCaptor<Person> arg = ArgumentCaptor.forClass(Person.class);

        Person person = new Person();
        person.setName("张三");
        list.add(person);

        Mockito.verify(list).add(arg.capture());

        assertEquals("张三", arg.getValue().getName());
    }
    
    class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * BDD行为驱动开发 案例
     */
    @Test
    public void testShouldBuyBread() {
        Seller seller = Mockito.mock(Seller.class);
        Shop shop = new Shop(seller);
        Bread bread = new Bread();
        // given
        BDDMockito.given(seller.askForBread()).willReturn(bread);
        //Mockito.when(seller.askForBread()).thenReturn(bread);

        // when
        Goods goods = shop.buyBread();
        // then
        assertThat(goods, is(bread));
    }

    class Seller {
        public Bread askForBread() { // 卖面包
            return null; // new Bread();
        }
    }
    class Shop {
        private Seller seller;
        private Goods goods;
        Shop(Seller seller){
            this.seller = seller;
        }
        public Goods buyBread() { // 买面包
            goods = seller.askForBread(); // 从卖家拿面包
            return goods;
        }
    }
    interface Goods {}
    class Bread implements Goods {}

    /**
     * mock细节
     */
    @Test
    public void testMockingDetails() throws Throwable {
        List<String> mock = Mockito.mock(List.class, invocation -> "element");
        List spy = Mockito.spy(List.class);
        // 判断对象是Mock还是Spy
        boolean isMock = Mockito.mockingDetails(mock).isMock();
        boolean isSpy = Mockito.mockingDetails(spy).isSpy();
        assertTrue(isMock);
        assertTrue(isSpy);
        // 获取mock详细，获取创建时的设置
        MockingDetails details = Mockito.mockingDetails(mock);
        Class<?> type = details.getMockCreationSettings().getTypeToMock();
        Answer<?> defaultAnswer = details.getMockCreationSettings().getDefaultAnswer();
        assertEquals(List.class, type);
        assertEquals("element", defaultAnswer.answer(null));
        // 获取调用和存根
        Mockito.doReturn(true).when(mock).add(Mockito.anyString());
        Mockito.when(mock.get(0)).thenReturn("1");
        Mockito.when(mock.get(1)).thenReturn("2");
        Mockito.when(mock.get(2)).thenReturn("3");
        mock.add("1");
        mock.get(0);
        mock.get(1);
        System.out.println("====获取调用====");
        Collection<Invocation> invocations = details.getInvocations();
        for (Invocation invocation : invocations) {
            System.out.print(invocation.getMethod().getName() + "(");
            Object[] arguments = invocation.getArguments();
            for (int i=0; i<arguments.length; i++) {
                if(i == arguments.length-1) {
                    System.out.println(arguments[i] + ")");
                }else{
                    System.out.print(arguments[i] + ",");
                }
            }
        }
        System.out.println("====获取存根====");
        Collection<Stubbing> stubbings = details.getStubbings();
        for (Stubbing stubbing : stubbings) {
            System.out.print(stubbing.getInvocation().getMethod().getName() + "(");
            Object[] arguments = stubbing.getInvocation().getArguments();
            for (int i=0; i<arguments.length; i++) {
                if(i == arguments.length-1) {
                    System.out.println(arguments[i] + ")");
                }else{
                    System.out.print(arguments[i] + ",");
                }
            }
        }
        System.out.println("====打印所有的调用，包含存根，未使用的存根====");
        // 打印所有的调用，包含存根，未使用的存根
        System.out.println(details.printInvocations());
    }

    /**
     * 参数匹配器
     */
    @Test
    public void testArgsMatchers() {
        List<String> list = Mockito.mock(List.class);
        Mockito.when(list.addAll(Mockito.argThat(new IsListOfTwoElements()))).thenReturn(true);

        assertTrue(list.addAll(Arrays.asList("one", "two", "three")));
        assertFalse(list.addAll(Arrays.asList("one", "two")));
    }

    class IsListOfTwoElements implements ArgumentMatcher<List> {
        @Override
        public boolean matches(List list) {
            return list.size() == 3;
        }
    }

    /**
     * 预期回调接口，生成期望值
     */
    @Test
    public void testAnswer() {
        List<String> mockList = Mockito.mock(List.class);
        //Mockito.when(mockList.get(Mockito.anyInt())).thenAnswer(new CustomAnswer());
        Mockito.doAnswer(new CustomAnswer()).when(mockList).get(Mockito.anyInt());
        // 或
        Mockito.doAnswer(invocation -> "hello world:" + invocation.getArguments()[0]).when(mockList).get(Mockito.anyInt());

        assertEquals("hello world:0", mockList.get(0));
        assertEquals("hello world:1", mockList.get(1));
    }

    private class CustomAnswer implements Answer<String> {
        @Override
        public String answer(InvocationOnMock invocation) throws Throwable {
            Object[] args = invocation.getArguments();
            return "hello world:" + args[0];
        }
    }

    /**
     * 对为预设的行为，设置默认预期值
     */
    @Test
    public void testDefaultAnswer() {
        List<String> mock = Mockito.mock(List.class, new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return 999;
            }
        });

        assertEquals(999, mock.get(1));
        assertEquals(999, mock.size());
        assertEquals(999, mock.remove(1));
    }

    /**
     * spy监控真实对象，设置真实对象行为
     */
    @Test
    public void testSpyOnRealObjects() {
        List<Integer> list = new LinkedList<>();
        List<Integer> spy = Mockito.spy(list);
        // when-thenReturn会抛异常，因为get(0)行为值不存在
        Mockito.doReturn(999).when(spy).get(999);
        Mockito.when(spy.size()).thenReturn(100);
        spy.add(1);
        spy.add(2);

        assertEquals(100, spy.size());
        assertEquals(new Integer(1), spy.get(0));
        assertEquals(new Integer(2), spy.get(1));
        assertEquals(new Integer(999), spy.get(999));

    }

    /**
     * 设置void方法什么也不做
     */
    @Test
    public void testDoNothing() {
        C c = Mockito.mock(C.class);
        // void方法才能调doNothing()
        Mockito.doNothing().when(c).setName(Mockito.anyString());
        c.setName("bb");
        assertEquals(null, c.getName());
    }

    public class C {
        private String name;
        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }

    /**
     * 调用真实方法
     */
    @Test
    public void testDoCallRealMethod() {
        D d = Mockito.mock(D.class);

        // 预设了bb
        Mockito.when(d.getName()).thenReturn("bb");
        assertEquals("bb", d.getName());

        // 真实返回了zhangsan
        // 等价于when(c.getName()).doCallRealMethod()
        Mockito.doCallRealMethod().when(d).getName();
        assertEquals("zhangsan", d.getName());
    }

    class D {
        public String getName() {
            return "zhangsan";
        }
    }

    /**
     * 重置mock
     */
    @Test
    public void testResetMock() {
        List list = Mockito.mock(List.class);
        Mockito.when(list.size()).thenReturn(10);
        list.add(1);
        assertEquals(10, list.size());

        Mockito.reset(list);
        assertEquals(0, list.size());
    }

    @Mock
    private List mockList;

    /*public MockitoTest() {
        // 注解初始化mock，或使用@RunWith(MockitoJUnitRunner.class)运行器
        MockitoAnnotations.initMocks(this);
    }*/

    /**
     * Mock注解
     */
    @Test
    public void testMockAnnotation() {
        mockList.add(1);
        Mockito.verify(mockList).add(1);
    }


    @MockBean
    private MemberReadHistoryRepository memberReadHistoryRepository;
    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    /**
     * MockBean注解
     * 模拟对象，需预期行为才能使用。
     */
    @Test
    public void testMockBeanAnnotation() {
        MemberReadHistory mrh = new MemberReadHistory();
        Mockito.doReturn(mrh).when(memberReadHistoryRepository).save(Mockito.any(MemberReadHistory.class));
        MemberReadHistory memberReadHistory = new MemberReadHistory();
        memberReadHistory.setMemberId(2L);
        memberReadHistory.setMemberNickname("hello");
        memberReadHistory.setMemberIcon("hello.icon");
        memberReadHistory.setProductId(1L);
        memberReadHistory.setProductName("hello product");
        memberReadHistory.setProductPic("product.jpg");
        memberReadHistory.setProductSubTitle("is a product");
        memberReadHistory.setProductPrice("100000");
        memberReadHistory.setCreateTime(new Date());
        int i = memberReadHistoryService.create(memberReadHistory);
        assertEquals(1, i);
    }


    @SpyBean
    private MemberReadHistoryService mrhs;
    /**
     * SpyBean注解
     * 使用真实对象，可选择性预期行为。
     */
    @Test
    public void testSpyBeanAnnotation() {
        Mockito.doReturn(2).when(mrhs).create(null);

        int i = mrhs.test();
        assertEquals(2, i);
    }




}
