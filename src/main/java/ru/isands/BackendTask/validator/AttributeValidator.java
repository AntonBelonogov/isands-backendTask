package ru.isands.BackendTask.validator;

import ru.isands.BackendTask.exception.EntityNotValidException;
import ru.isands.BackendTask.model.Appliance;
import ru.isands.BackendTask.model.Model;

import java.util.HashMap;
import java.util.Map;

public class AttributeValidator {
    public static void compareModel(Appliance appliance, Model model) {
        Map<String, Object> applianceAttribute = new HashMap<>(appliance.getApplianceAttributes());
        Map<String, Object> modelAttribute = new HashMap<>(model.getModelAttributes());
        if (applianceAttribute.size() != modelAttribute.size()) {
            throw new EntityNotValidException(String.format("Attributes size equals: appliance = %d, model = %d.",
                    applianceAttribute.size(),
                    modelAttribute.size()));
        }
        for (String key : applianceAttribute.keySet()) {
            if (!modelAttribute.containsKey(key)) {
                throw new EntityNotValidException("Attributes not contains " + key + ".");
            }

            Object firstValue = applianceAttribute.get(key);
            Object secondValue = modelAttribute.get(key);

            if (!compareValueClass(firstValue, secondValue)) {
                throw new EntityNotValidException("Attributes not valid.");
            }
        }
    }

    private static boolean compareValueClass(Object firstValue, Object secondValue) {
        if (firstValue == null && secondValue == null) {
            throw new EntityNotValidException("Both of values are null.");
        }
        if (firstValue == null || secondValue == null) {
            throw new EntityNotValidException("One of value is null.");
        }
        if (firstValue.getClass() != secondValue.getClass()) {
            throw new EntityNotValidException("Appliance value class not equals model value class.");
        }
        return true;
    }
}
