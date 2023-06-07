package reestr.service;


import reestr.model.ModelDetails;
import reestr.model.Smartphone;

import java.util.List;

public interface ModelService {

    ModelDetails addNewModel(ModelDetails modelDetails, Integer typeId, Integer lineId);

    List<ModelDetails> getSortedByPriceAndAlphabet(Integer typeId, String sort);

    List<ModelDetails> getAllModels();

    List<ModelDetails> findByNameAndFilterByParams(Integer typeId, String name, String colour, String from, String to);
}
