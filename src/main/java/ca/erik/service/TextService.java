package ca.erik.service;

import ca.erik.model.Text;
import org.springframework.stereotype.Service;

@Service
public interface TextService {
    Text create(Text text);
}
