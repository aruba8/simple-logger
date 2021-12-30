package ca.erik.service.impl;

import ca.erik.model.Text;
import ca.erik.repository.TextRepository;
import ca.erik.service.TextService;
import org.springframework.stereotype.Service;

@Service
public class TextServiceImpl implements TextService {
    private final TextRepository textRepository;

    public TextServiceImpl(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @Override
    public Text create(Text text) {
        return textRepository.insert(text);
    }
}
