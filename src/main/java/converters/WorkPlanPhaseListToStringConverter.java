
package converters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.WorkPlanPhaseService;
import domain.WorkPlanPhase;

@Transactional
@Component
public class WorkPlanPhaseListToStringConverter implements Converter<List<WorkPlanPhase>, String> {

	@Autowired
	private WorkPlanPhaseService	workPlanPhaseService;


	@Override
	public String convert(List<WorkPlanPhase> list) {
		String res = "";

		if (list == null || list.isEmpty()) {
			res = null;
		} else {
			for (int i = 0; i < list.size(); i++) {
				res = res + list.get(i).getId();
				if (i < list.size() - 1) {
					res = res + ",";
				}
			}
		}

		return res;
	}

}
