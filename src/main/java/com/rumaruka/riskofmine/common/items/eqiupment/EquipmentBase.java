package com.rumaruka.riskofmine.common.items.eqiupment;

import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.Types;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;

public class EquipmentBase extends BaseCollectablesItem {
    private final Category categoryEnum;

    public int cooldownMinus;

    public EquipmentBase(Category categoryEnum) {
        super(Types.EQUIPMENT, categoryEnum, 1);
        this.categoryEnum = categoryEnum;

    }
}
