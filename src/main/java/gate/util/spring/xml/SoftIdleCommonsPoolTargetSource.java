/*
 *  SoftIdleCommonsPoolTargetSource.java
 *
 *  Copyright (c) 1995-2012, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 2, June 1991 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 *
 *  Ian Roberts, 19/Oct/2017
 *
 *  $Id: PoolFiller.java 17530 2014-03-04 15:57:43Z markagreenwood $
 */
package gate.util.spring.xml;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.springframework.aop.target.CommonsPoolTargetSource;

/**
 * CommonsPoolTargetSource that sets the softMinEvictableIdleTimeMillis on the
 * underlying pool instead of the "hard" minEvictableIdleTimeMillis. The "soft"
 * one respects minIdle, the "hard" one shuts down all idle instances ignoring
 * minIdle.
 * 
 * @author Ian Roberts
 */
public class SoftIdleCommonsPoolTargetSource extends CommonsPoolTargetSource {

  private static final long serialVersionUID = -6668052870905847355L;

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  protected ObjectPool createObjectPool() {
    GenericObjectPool gop = new GenericObjectPool(this);
    gop.setMaxActive(getMaxSize());
    gop.setMaxIdle(getMaxIdle());
    gop.setMinIdle(getMinIdle());
    gop.setMaxWait(getMaxWait());
    gop.setTimeBetweenEvictionRunsMillis(getTimeBetweenEvictionRunsMillis());
    // Next line is the only difference from superclass - set "soft"
    // rather than "hard" minEvictableIdleTimeMillis
    gop.setSoftMinEvictableIdleTimeMillis(getMinEvictableIdleTimeMillis());
    gop.setWhenExhaustedAction(getWhenExhaustedAction());
    return gop;
  }
  
}
