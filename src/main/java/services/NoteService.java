
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.NoteRepository;
import domain.Note;
import domain.Report;

@Service
@Transactional
public class NoteService {

	@Autowired
	private NoteRepository	noteRepo;

	@Autowired
	private ActorService	actorService;


	public Note create() {
		Note res = new Note();

		return res;
	}

	public Note initialize(Note note) {
		note.setPublishDate(new Date());
		note.setAuthor(this.actorService.findPrincipal());
		note.setComments(new ArrayList<String>());

		return this.save(note);
	}

	public Note save(Note note) {
		return this.noteRepo.save(note);
	}

	public Note findById(int noteId) {
		return this.noteRepo.findOne(noteId);
	}

	public List<Note> findByReport(Report report) {
		return this.noteRepo.findByReport(report.getId());
	}
}
