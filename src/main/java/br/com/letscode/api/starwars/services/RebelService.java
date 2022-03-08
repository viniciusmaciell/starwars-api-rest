package br.com.letscode.api.starwars.services;

import br.com.letscode.api.starwars.models.RebelModel;
import br.com.letscode.api.starwars.repositories.RebelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RebelService {

    private final RebelRepository repository;

    public RebelService(RebelRepository repository) {
        this.repository = repository;
    }

    public List<RebelModel> getAll() {
        return repository.getAll();

    }

    public RebelModel cadastrar(RebelModel rebel) {
        repository.cadastrar(rebel);
        return rebel;
    }
}
