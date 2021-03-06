package chapter2.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import chapter2.bookstore.domain.BookRepository;
import chapter2.bookstore.domain.CategoryRepository;
import chapter2.bookstore.domain.Book;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository; 
	@Autowired 
	private CategoryRepository crepository;
	
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookController(Model model){
		 model.addAttribute("book", repository.findAll());
		
		return "booklist";
	}
	
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest(){
		return (List<Book>) repository.findAll();
	}
	
	@RequestMapping(value="/books/{id}", method=RequestMethod.GET)
	public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId){
		return repository.findOne(bookId);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	  public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	    repository.delete(bookId);
	    return "redirect:../booklist";
	  }   
	
	@RequestMapping(value = "/add")
    public String addStudent(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "addBook";
    }   
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    } 
}
