package be.teletask.onvif.models.requests;

import be.teletask.onvif.models.OnvifType;

/**
 * Created by Tomas Verhelst on 03/09/2018.
 * Copyright (c) 2018 TELETASK BVBA. All rights reserved.
 */
public interface OnvifRequest {

    String getXml();

    OnvifType getType();

}
