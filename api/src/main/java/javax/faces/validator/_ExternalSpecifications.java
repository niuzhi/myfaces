/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package javax.faces.validator;

import javax.validation.Validation;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Package-private utility class for determining which specifications are available
 * in the current process. See JIRA issue: http://issues.apache.org/jira/browse/MYFACES-2386
 * </p>
 *
 * @author Jan-Kees van Andel
 * @since 2.0
 */
class _ExternalSpecifications
{

    //private static final Log log = LogFactory.getLog(BeanValidator.class);
    private static final Logger log = Logger.getLogger(_ExternalSpecifications.class.getName());

    /**
     * This boolean indicates if Bean Validation is present.
     *
     * Eager initialization is used for performance. This means Bean Validation binaries
     * should not be added at runtime after this variable has been set.
     */
    static final boolean isBeanValidationAvailable;
    static
    {
        boolean tmp = false;
        try
        {
            tmp = (Class.forName("javax.validation.Validation") != null);

            if (tmp)
            {
                try
                {
                    // Trial-error approach to check for Bean Validation impl existence.
                    Validation.buildDefaultValidatorFactory().getValidator();
                }
                catch (Throwable t)
                {
                    log.log(Level.FINE, "Error initializing Bean Validation (could be normal)", t);
                    tmp = false;
                }
            }
        }
        catch (Throwable t)
        {
            log.log(Level.FINE, "Error loading class (could be normal)", t);
            tmp = false;
        }
        isBeanValidationAvailable = tmp;
    }

    /**
     * This boolean indicates if Unified EL is present.
     *
     * Eager initialization is used for performance. This means Unified EL binaries
     * should not be added at runtime after this variable has been set.
     */
    static final boolean isUnifiedELAvailable;
    static
    {
        boolean tmp = false;
        try
        {
            //TODO: Check this class name when Unified EL for Java EE6 is final.
            tmp = (Class.forName("javax.el.ValueReference") != null);
        }
        catch (Throwable t)
        {
            log.log(Level.FINE, "Error loading class (could be normal)", t);
            tmp = false;
        }
        isUnifiedELAvailable = tmp;
    }

}
