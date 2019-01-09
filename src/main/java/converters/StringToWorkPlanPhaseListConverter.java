
package converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.WorkPlanPhaseService;
import domain.WorkPlanPhase;

@Transactional
@Component
public class StringToWorkPlanPhaseListConverter implements Converter<String, List<WorkPlanPhase>> {

	@Autowired
	private WorkPlanPhaseService	workPlanPhaseService;


	@Override
	public List<WorkPlanPhase> convert(String text) {
		List<WorkPlanPhase> res = new ArrayList<>();

		try {
			if (StringUtils.isEmpty(text)) {
				//do nothing
			} else {
				String ids[] = text.split(",");
				for (int i = 0; i < ids.length; i++) {
					WorkPlanPhase phase = this.workPlanPhaseService.findById(Integer.valueOf(ids[i].trim()));
					res.add(phase);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return res;
	}

}
