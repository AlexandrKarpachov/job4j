package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class StartUITest {

    /**
     * this constant stores the number of menu items
     */
    private final int menuCount = 6;
    private Tracker tracker;
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final String menu = new StringBuilder()
            .append("0. Add new Item\r\n")
            .append("1. Show all items\r\n")
            .append("2. Edit item\r\n")
            .append("3. Delete item\r\n")
            .append("4. Find item by Id\r\n")
            .append("5. Find items by name\r\n")
            .toString();

    List<Integer> range;


    @Before
    public void before() {
        range = new ArrayList<>();
        for (int i = 0; i < menuCount; i++) {
            range.add(i);
        }
        this.tracker = new Tracker();
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(stdOut);
    }


    @Test
    public void whenAddOneItem() {
        String[] sequence = {"0", "name1", "test description", "y"};
        StubInput input = new StubInput(sequence);
        new StartUI(input, this.tracker).init();
        assertThat(this.tracker.findAll().get(0).getName(), is("name1"));
    }

    @Test
    public void whenAddTwoItemsAndShowAll() {
        String[] sequence = {"1", "y"};
        this.tracker.add(new Item("name1", "desc1"));
        this.tracker.add(new Item("name2", "desc2"));
        StubInput input = new StubInput(sequence);
        new StartUI(input, tracker).init();
        assertThat(out.toString(), is(
                new StringBuilder()
                .append(this.menu)
                .append(this.tracker.findAll().get(0).getName() + " ")
                .append(this.tracker.findAll().get(0).getId())
                .append(System.lineSeparator())
                .append(this.tracker.findAll().get(0).getTime())
                .append(System.lineSeparator())
                .append(this.tracker.findAll().get(1).getName() + " ")
                .append(this.tracker.findAll().get(1).getId())
                .append(System.lineSeparator())
                .append(this.tracker.findAll().get(1).getTime())
                .append(System.lineSeparator())
                .toString()
        ));
    }

    @Test
    public void findByWrongID() {
        String[] sequence = {"4", "wrong id", "y"};
        StubInput input = new StubInput(sequence);
        new StartUI(input, tracker).init();
        assertThat(out.toString(), is(
                new StringBuilder()
                        .append(this.menu)
                        .append("Sorry, Application with such ID does not exist\r\n")
                        .toString()
        ));
    }

    @Test
    public void findByID() {
        Item item = new Item("name1", "desc1");
        this.tracker.add(item);
        String[] sequence = {"4", item.getId(), "y"};
        StubInput input = new StubInput(sequence);
        new StartUI(input, tracker).init();
        assertThat(out.toString(), is(
                new StringBuilder()
                        .append(this.menu)
                        .append(item.getName() + " ")
                        .append(item.getDecs() + "\r\n")
                        .append(item.getTime() + "\r\n")
                        .toString()
        ));
    }

    @Test
    public void findByNameWhenThreeItems() {
        String[] sequence = {"5", "name1", "y"};
        Item first = new Item("name1", "desc1");
        Item second = new Item("name1", "desc2");
        this.tracker.add(new Item("name2", "desc2"));
        this.tracker.add(first);
        this.tracker.add(second);
        StubInput input = new StubInput(sequence);
        new StartUI(input, tracker).init();
        assertThat(out.toString(), is(
                new StringBuilder()
                        .append(this.menu)
                        .append(first.getName() + " ")
                        .append(first.getDecs() + "\r\n")
                        .append(first.getTime() + "\r\n")
                        .append(second.getName() + " ")
                        .append(second.getDecs() + "\r\n")
                        .append(second.getTime() + "\r\n")
                        .toString()
        ));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Item item = this.tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "change ticket", "y"});
        new StartUI(input, this.tracker).init();
        assertThat(this.tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenAddTwoTicketAndDelete() {
        Item first = this.tracker.add(new Item("name1", "desc1"));
        Item second = this.tracker.add(new Item("name2", "desc2"));
        List<Item> expected = Arrays.asList(first);
        Input input = new StubInput(new String[]{"3", second.getId(), "y"});
        new StartUI(input, this.tracker).init();
        assertThat(expected, is(this.tracker.findAll()));
    }

    @Test
    public void whenAddOneTicketAndDelete() {
        Item first = this.tracker.add(new Item("name1", "desc1"));
        Input input = new StubInput(new String[]{"3", first.getId(), "y"});
        new StartUI(input, this.tracker).init();
        assertThat(new ArrayList<>(), is(this.tracker.findAll()));
    }

    @Test
    public void whenInvalidDataInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"invalid", "1"})
        );
        input.ask("Enter", this.range);
        assertThat(
                this.out.toString(),
                is(
                        String.format("Please enter valid data%n")
                )
        );
    }

    @Test
    public void whenInvalidRangeInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"7", "1"})
        );
        input.ask("Enter", this.range);
        assertThat(
                this.out.toString(),
                is(
                        String.format("Please enter valid point%n")
                )
        );
    }

    @Test
    public void whenInvalidRangeTwiceInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"7", "invalid data", "1"})
        );
        input.ask("Enter", this.range);
        assertThat(
                this.out.toString(),
                is(
                        String.format(
                                "%s%n%s%n",
                                "Please enter valid point",
                                "Please enter valid data"
                        )
                )
        );
    }


}
